

from Annotation import Annotation
from Annotator import Annotator

import json
from tornado.ioloop import IOLoop
from tornado.web import Application


class Color(Annotation):
    def __init__(self, color, start, end):
        self.color = color
        self.begin = start
        self.end = end


class ColorAnnotator(Annotator):
    def initialize(self):
        super().initialize()
        self.color_words = ['red', 'blue', 'yellow']
        self.annotation_types.append(Color)

    def process(self, data):
        sofa_string = data['_referenced_fss']['1']['sofaString']
        for word in self.color_words:
            if word in sofa_string:
                self.add_annotation(Color(word, -1, -1))


if __name__ == '__main__':
    app = Application([
        ('/', ColorAnnotator)
    ])
    app.listen(8080)
    IOLoop.current().start()
