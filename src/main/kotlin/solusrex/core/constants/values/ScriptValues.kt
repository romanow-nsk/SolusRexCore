package solusrex.core.constants.values

import solusrex.core.constants.CONST

class ScriptValues {
    public companion object {
        //---------------------  Группы типов данных ЯОC -------------------------------------------------------------
        @CONST(group = "DTGroup", title = "undefined")
        val DTGUndefuned = 0
        @CONST(group = "DTGroup", title = "symbols")
        val DTGSymbol = 1
        @CONST(group = "DTGroup", title = "integer")
        val DTGInteger = 2
        @CONST(group = "DTGroup", title = "real")
        val DTGReal = 3
        @CONST(group = "DTGroup", title = "logical")
        val DTGLogical = 4
        //---------------------  Типы данных ЯОC -------------------------------------------------------------
        @CONST(group = "DType", title = "void", className = "DTGUndefuned")
        val DTVoid = 0
        @CONST(group = "DType", title = "short", className = "DTGInteger")
        val DTShort = 1
        @CONST(group = "DType", title = "int", className = "DTGInteger")
        val DTInt = 2
        @CONST(group = "DType", title = "long", className = "DTGInteger")
        val DTLong = 3
        @CONST(group = "DType", title = "float", className = "DTGReal")
        val DTFloat = 4
        @CONST(group = "DType", title = "double", className = "DTGReal")
        val DTDouble = 5
        @CONST(group = "DType", title = "boolean", className = "DTGLogical")
        val DTBoolean = 6
        @CONST(group = "DType", title = "char", className = "DTGUndefuned")
        val DTChar = 7
        @CONST(group = "DType", title = "date", className = "DTGUndefuned")
        val DTDateTime = 8
        @CONST(group = "DType", title = "array", className = "DTGUndefuned")
        val DTArray = 9
        @CONST(group = "DType", title = "string", className = "DTGSymbol")
        val DTString = 10
        //val DTGroupNames = arrayOf("void", "string", "int", "double", "boolean")
        //val DTGTypes = intArrayOf(DTVoid, DTString, DTLong, DTDouble, DTBoolean)
        //---------------------  Операции ЯОП -----------------------------------------------------------------
        @CONST(group = "Operation", title = "nop")
        val ONOP = 0
        @CONST(group = "Operation", title = "add")
        val OAdd = 1
        @CONST(group = "Operation", title = "sub")
        val OSub = 2
        @CONST(group = "Operation", title = "mul")
        val OMul = 3
        @CONST(group = "Operation", title = "div")
        val ODiv = 4
        @CONST(group = "Operation", title = "mod")
        val OMod = 5
        @CONST(group = "Operation", title = "and")
        val OAnd = 6
        @CONST(group = "Operation", title = "or")
        val OOr = 7
        @CONST(group = "Operation", title = "not")
        val ONot = 8
        @CONST(group = "Operation", title = "eq")
        val OEqual = 9
        @CONST(group = "Operation", title = "neq")
        val ONoEqual = 10
        @CONST(group = "Operation", title = "le")
        val OLE = 11
        @CONST(group = "Operation", title = "lt")
        val OLT = 12
        @CONST(group = "Operation", title = "gt")
        val OGT = 13
        @CONST(group = "Operation", title = "ge")
        val OGE = 14
        @CONST(group = "Operation", title = "jmp")
        val OJmp = 15
        @CONST(group = "Operation", title = "jfalse")
        val OJmpFalse = 16
        @CONST(group = "Operation", title = "jtrue")
        val OJmpTrue = 17
        @CONST(group = "Operation", title = "return")
        val OReturn = 18
        @CONST(group = "Operation", title = "push")
        val OPush = 19
        @CONST(group = "Operation", title = "pushVar")
        val OPushVar = 20
        @CONST(group = "Operation", title = "pop")
        val OPop = 21
        @CONST(group = "Operation", title = "pow")
        val OPow = 22
        @CONST(group = "Operation", title = "save")
        val OSave = 23
        @CONST(group = "Operation", title = "call")
        val OCall = 24
        @CONST(group = "Operation", title = "andWord")
        val OAndW = 25
        @CONST(group = "Operation", title = "orWord")
        val OOrW = 26
        @CONST(group = "Operation", title = "notWord")
        val ONotW = 27
        @CONST(group = "Operation", title = "xorWord")
        val OXorW = 28
        @CONST(group = "Operation", title = "xor")
        val OXor = 29
        @CONST(group = "Operation", title = "convert")
        val OConvert = 30
        //------------------- Типы ошибок ЯОП -----------------------------------------------------------------
        @CONST(group = "SEType", title = "Не определено")
        val SETypeUndef = 0
        @CONST(group = "SEType", title = "Компиляция")
        val SETypeCompile = 1
        @CONST(group = "SEType", title = "Связывание")
        val SETypeLink = 2
        @CONST(group = "SEType", title = "Исполнение")
        val SETypeRunTime = 2
        @CONST(group = "SEMode", title = "Информация")
        val SEModeInfo = 0
        @CONST(group = "SEMode", title = "Предупреждение")
        val SEModeWarning = 1
        @CONST(group = "SEMode", title = "Ошибка")
        val SEModeError = 2
        @CONST(group = "SEMode", title = "Крах")
        val SEModeFatal = 3
        //------------------------ Коды ошибок runtime ---------------------------------------------------------
        @CONST(group = "SError", title = "Недопустимый код ошибки")
        val SENoCode = 0
        @CONST(group = "SError", title = "Переполнение стека")
        val SEStackOver = 1
        @CONST(group = "SError", title = "Стек пуст")
        val SEStackEmpty = 2
        @CONST(group = "SError", title = "Выход за границы стека")
        val SEStackLimits = 3
        @CONST(group = "SError", title = "Формат целого")
        val SEIntFormat = 4
        @CONST(group = "SError", title = "Форматирование целого")
        val SEIntOutFormat = 5
        @CONST(group = "SError", title = "Недопустимая операция для ТД")
        val SEIllegalOperation = 6
        @CONST(group = "SError", title = "Ошибка конвертации float/int")
        val SEFloatConvertation = 7
        @CONST(group = "SError", title = "Недопустимое сравнение для ТД")
        val SEIllegalCompare = 8
        @CONST(group = "SError", title = "Переменная не определена")
        val SEIllegalVariable = 9
        @CONST(group = "SError", title = "Недопустимый ТД")
        val SEIllegalDT = 10
        @CONST(group = "SError", title = "Ошибка вызова функции")
        val SEFunctionCall = 11
        @CONST(group = "SError", title = "Недопустимый формат")
        val SEIllegalFormat = 12
        @CONST(group = "SError", title = "Программная ошибка (баг)")
        val SEBug = 13
        @CONST(group = "SError", title = "Программная ошибка класса функции(баг)")
        val SECreateFunctionBug = 14
        @CONST(group = "SError", title = "Ошибка конфигурации")
        val SEConfiguration = 15
        @CONST(group = "SError", title = "Недопустимое окружение вызова функции")
        val SEIllegalFunEnv = 16
        //--------------------------Коды ошибок компиляции ----------------------------------------------------
        @CONST(group = "SError", title = "Не найден конец текста")
        val SENoEOF = 100
        @CONST(group = "SError", title = "Пропущено имя переменной ")
        val SENoVarName = 101
        @CONST(group = "SError", title = "Переменная не определена")
        val SEVarNotDef = 102
        @CONST(group = "SError", title = "Повторное определение переменной")
        val SEVarMultiply = 103
        @CONST(group = "SError", title = "Ошибка списка переменных")
        val SEVarListFormat = 104
        @CONST(group = "SError", title = "Не найден символ")
        val SELexemLost = 105
        @CONST(group = "SError", title = "Недопустимый оператор")
        val SEIllegalOperator = 106
        @CONST(group = "SError", title = "Недопустимое условие")
        val SEIllegalCondition = 107
        @CONST(group = "SError", title = "Формат константы")
        val SEConstFormat = 108
        @CONST(group = "SError", title = "Синтаксическая ошибка")
        val SEIllegalSyntax = 109
        @CONST(group = "SError", title = "Недопустимый тип выражения")
        val SEIllegalExprDT = 110
        @CONST(group = "SError", title = "Недопустимое сочетание ТД")
        val SEIllegalTypeConvertion = 111
        @CONST(group = "SError", title = "Ошибка преобразования ТД")
        val SEDataType = 112
        @CONST(group = "SError", title = "Недопустимый символ")
        val SEIllegalSymbol = 113
        @CONST(group = "SError", title = "Синтаксическая ошибка функции")
        val SEIllegalFunSyntax = 114
        @CONST(group = "SError", title = "Функция не определена")
        val SEFunNotDefined = 115
        //------------------------------------ Классы функций --------------------------------------
        const val StdScriptFunPackage = "romanow.abc.core.script.functions"
        @CONST(group = "ScriptFunStd", title = "sin", className = "FStdSin")
        val SFSin = 1
        @CONST(group = "ScriptFunStd", title = "cos", className = "FStdCos")
        val SFCos = 2
        @CONST(group = "ScriptFunStd", title = "alert", className = "FStdAlert")
        val SFAlert = 3
        }
    }