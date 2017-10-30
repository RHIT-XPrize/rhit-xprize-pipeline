from tornado.web import RequestHandler
import json


class Annotator(RequestHandler):
    def process(self, data, files):
        raise NotImplemented('Annotators must implement `process` method')

    def initialize(self):
        self.annotation_types = []
        self._annotations = []

    def post(self):
        body = json.loads(self.request.body.decode())
        files = self.request.files
        self.process(body, files)
        resp = json.dumps(self._annotation_to_dict(self._annotations))
        self.write(resp)

    def add_annotation(self, annotation):
        self._annotations.append(annotation)

    def _annotation_to_dict(self, annotations):
        annot_map = {c.__name__ : [] for c in self.annotation_types}
        for x in annotations:
            class_name = x.name()
            annot_map[class_name].append(dict(x))
        return annot_map
