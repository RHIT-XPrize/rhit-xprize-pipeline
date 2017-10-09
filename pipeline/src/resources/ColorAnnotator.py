
import tornado.web
import socket
import tornado.ioloop
import json


class Annotator(tornado.web.RequestHandler):
    def __init__(self):
        self._addr = '127.0.0.1'
        self._port = 8080
        self._stream = None

    def _initialize(self, sock):
        client_socket, _ = sock.accept()
        self._stream = tornado.iostream.IOStream(client_socket)
        # self._stream.connect((self._addr, self._port))

    def _handle_request(self, sock, fd, events):
        self._initialize(sock)
        self._stream.read_until(b'$%$', callback=self._annotate)

    def _annotate(self, data):
        resp = self.annotate(json.loads(data.decode()[:-3]))
        self.respond(resp)

    def respond(self, data):
        if data:
            self._stream.write(bytes(json.dumps(data) + "\n", 'UTF-8'))
        self._stream.write(bytes("{}\n", 'UTF-8'))

    def start(self):
        print('Starting annotator')
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM, 0)
        sock.bind((self._addr, self._port))
        sock.listen(5)

        io_loop = tornado.ioloop.IOLoop.current()
        io_loop.add_handler(sock.fileno(),
                            lambda fd, events: self._handle_request(sock, fd, events),
                            io_loop.READ)
        io_loop.start()


class ColorAnnotator(Annotator):
    def __init__(self):
        super().__init__()
        self.color_words = ['red', 'blue', 'yellow']

    def annotate(self, data):
        sofa_string = data['_referenced_fss']['1']['sofaString']
        for word in sofa_string.split(' '):
            if word in self.color_words:
                return {
                    'color': word,
                    'begin': -1,
                    'end': -1
                }
        return False


if __name__ == '__main__':
    annotator = ColorAnnotator()
    annotator.start()
