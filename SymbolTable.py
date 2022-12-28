from llvmlite import ir

# Constants
ERROR_TYPE_REDEFINATION = "Type Redefination"
ERROR_TYPE_UNDEFINED = "Type Undefined"


class SymbolTable:
    '''
    符号表类
    '''
    def __init__(self):
        '''
        功能：建立符号表
        参数：无
        返回：无
        '''
        #table：table[i]是一个字典，存着key，value组
        self.Table = [{}]
        self.CurrentLevel = 0  

    def GetItem(self, item):
        '''
        功能：从符号表中获取元素
        参数：待获取的元素的key
        返回：成功：返回元素，失败返回None
        '''
        i = self.CurrentLevel
        while i >= 0:
            TheItemList = self.Table[i]
            if item in TheItemList:
                return TheItemList[item]
            i -= 1
        return None

    def AddItem(self, key, value):
        '''
        功能：向符号表中添加元素
        参数：待添加的key，value
        返回：成功{"result":"success"}，失败{"result":"fail","reason":具体原因码}
        '''
        if key in self.Table[self.CurrentLevel]:
            Result = {"result":"fail", "reason": ERROR_TYPE_REDEFINATION}
            return Result
        self.Table[self.CurrentLevel][key] = value
        return {"result":"success"}

    def JudgeExist(self, item):
        '''
        功能：判断元素是否在符号表里
        参数：待判断的元素
        返回：如果表里有，true，否则false
        '''
        i = self.CurrentLevel
        while i >= 0:
            if item in self.Table[i]:
                return True
            i -= 1
        return False

    def EnterScope(self):
        '''
        功能：进入一个新的作用域，增加一层
        参数：无
        返回：无
        '''
        self.CurrentLevel += 1
        self.Table.append({})

    def QuitScope(self):
        '''
        功能：退出一个作用域，退出一层
        参数：无
        返回：无
        '''
        if self.CurrentLevel == 0:
            return
        self.Table.pop(-1)
        self.CurrentLevel -= 1
    
    def JudgeWhetherGlobal(self):
        '''
        功能：判断当前变量是否全局
        参数：无
        返回：是true，否则false
        '''
        if len(self.Table) == 1:
            return True
        else:
            return False
