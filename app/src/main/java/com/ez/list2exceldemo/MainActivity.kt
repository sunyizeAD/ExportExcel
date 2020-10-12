package com.ez.list2exceldemo

import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ez.list2exceldemo.bean.GradeBean
import com.ez.list2exceldemo.utils.ExcelUtils
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.runtime.Permission
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity(),View.OnClickListener {
    private val title = arrayOf("序号","姓名","成绩")
    lateinit var data:ArrayList<ArrayList<String>>
    lateinit var students:ArrayList<GradeBean>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_export.setOnClickListener(this)
        initData()
    }

    private fun initData() {
        //模拟数据集合
        //模拟数据集合
        students = ArrayList<GradeBean>()
        for (i in 1..10) {
            students.add(GradeBean("99","小红"))
            students.add(GradeBean("56","小绿"))
            students.add(GradeBean("41","小紫"))
            students.add(GradeBean("100","小黑"))
        }
    }

    override fun onClick(v: View?) {
        when(v!!.id)
        {
            R.id.tv_export->{
                getPermission()
            }
        }
    }

    private fun getPermission() {
        AndPermission.with(this).runtime()
                .permission(Permission.Group.STORAGE)
                .onGranted {
                    exportExcle()
                }.start()
    }

    private fun exportExcle() {
        var file = File(getSDPath())
        file.mkdirs()
//        makeDir(file)
        var fileName = file.absolutePath+"/成绩.xls"
        ExcelUtils.initExcel(fileName,title,"test")
        ExcelUtils.writeObjListToExcel(getGradeData(),fileName,this)
    }

    private fun getGradeData():ArrayList<ArrayList<String>> {
         data  = ArrayList()
        for (index in 0..(students.size-1))
        {
            var  student = students[index]
            var row = ArrayList<String>()
            row.add((index+1).toString())
            row.add(student.name)
            row.add(student.grade)
            data.add(row)
        }
        return data
    }

    private fun getSDPath(): String? {
        var sdDir: File? = null
        val sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory()
        }
        return sdDir.toString()
    }

    fun makeDir(dir: File) {
        if (!dir.parentFile.exists()) {
            makeDir(dir.parentFile)
        }
        dir.mkdir()
    }
}