from typing import TypeVar, Generic

T = TypeVar('T')


class Result(Generic[T]):

    def __init__(self, success: bool, value: T = None, message: str = None):
        self.success = success
        self.value = value
        self.message = message

