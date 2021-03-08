function addTeacher() {
    var teacherNum = $('#modal-teacherNum').val()
    var teacherName = $('#modal-teacherName').val()
    var teacherSex = $('#modal-teacherSex').val()
    var teacherAge = $('#modal-teacherAge').val()
    var teacherDept = $('#modal-teacherDept').val()
    if(!teacherNum){
        alert("请输入教师编号！")
        return
    }else{
        var urlStr = ipPort + '/teaher/add?name=' + teacherNum + '&shortName=' + departmentShortName + '&description=' + departmentDescription
        $.ajax({
            url:urlStr,
            dataType:'json',
            success:function (obj) {
                if(obj.code == 8){
                    alert('新增失败, 部门简称只能是唯一的2位字母！')
                }else{
                    alert(obj.message)
                    getAllDepartmentInformation()
                }
            },
            error:function (error) {
                console.log(error)
            }
        })
    }
}