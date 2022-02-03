let pathName = window.document.location.pathname;
let projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
// 导入excel文件
$('#sendButton4EmpSal').on('click', function () {
  let formData = new FormData();
  formData.append("file", $("#employeeSalary4Excel")[0].files[0]);
  if (formData.get('file') === "undefined") {
    alert("请选择需要上传的表单")
  } else {
    $.ajax({
      url: "https://www.shengcong.club" + projectName + "/excel/readAndSendEmpSalExcelData",
      type: 'POST',
      async: false,
      data: formData,
      // 告诉jQuery不要去处理发送的数据
      processData: false,
      // 告诉jQuery不要去设置Content-Type请求头
      contentType: false,
      success: function (obj) {
        alert(obj);
        console.log(obj);
      }
    });
    $('#employeeSalary4Excel').replaceWith('<input id="employeeSalary4Excel" type="file" class="form-control" ' +
        'style="width: 1000px;display: inline;"/>');
  }
});