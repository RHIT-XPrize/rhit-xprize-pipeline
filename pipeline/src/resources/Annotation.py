
class Annotation():
    def __iter__(self):
        return self.__dict__.items().__iter__()

    def name(self):
        return type(self).__name__
