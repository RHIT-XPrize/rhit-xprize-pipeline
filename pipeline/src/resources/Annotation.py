class Annotation():
    def __iter__(self):
        return self.__dict__.items().__iter__()

    def name(self=None):
        raise NotImplemented('Annotations must implement `name` method')
