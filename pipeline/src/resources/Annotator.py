
from tornado.web import RequestHandler
import json


class Annotator(RequestHandler):

    def process(self, data):
        raise NotImplemented('Annotators must implement `process` method')

    def initialize(self):
        self._annotations = []

    def post(self):
        body = json.loads(self.request.body)
        self.process(body)
        self.write(json.dumps(self._annotation_to_dict(self._annotations)))

    def add_annotation(self, annotation):
        self._annotations.append(annotation)

    def _annotation_to_dict(self, annotations):
        annot_map = {}
        for x in annotations:
            class_name = x.name()
            if class_name in annot_map:
                annot_map[class_name].append(dict(x))
            else:
                annot_map[class_name] = [dict(x)]
        return annot_map
