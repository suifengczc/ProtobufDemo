package com.suifeng.protobufdemo.data

//解析byte流时当前数据属于什么值
const val DATA_TYPE_WIRE = 0 //wireType数据
const val DATA_TYPE_LENGTH = 1 //length数据
const val DATA_TYPE_VALUE = 2 //value数据

//wireType值,3和4废弃
const val WIRE_TYPE_0 = 0   //wiretType = 0
const val WIRE_TYPE_1 = 1   //wiretType = 1
const val WIRE_TYPE_2 = 2   //wiretType = 2
const val WIRE_TYPE_5 = 5   //wiretType = 5