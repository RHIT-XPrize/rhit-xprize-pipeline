
import tornado.web
import socket
import tornado.ioloop
import json

class Annotator(tornado.web.RequestHandler):
    def __init__(self):
        self._addr = '127.0.0.1'
        self._port = 8080
        self._stream = None

    def _initialize(self):
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM, 0)
        self._stream = tornado.iostream.IOStream(sock)
        self._stream.connect((self._addr, self._port))

    def _handle_request(self):
        self._stream.read_until('}', callback=self._annotate)

    def _annotate(self, data):
        resp = self.annotate(json.reads(data))
        self.respond(resp)

    def respond(self, data):
        self._stream.write(json.dumps(data))


class ColorAnnotator(Annotator):
    def __init__(self):
        super().__init__()
        self.color_words = ['red', 'blue', 'yellow']

    def annotate(self, data):
        return {
            'color': 'red',
            'begin': 5,
            'end': 10
        }
