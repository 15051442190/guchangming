$(function(){
    //1.使用datagrid组件绑定数据
    $('#dg').datagrid({
        url:'getUser',
        pagination:true,  //开启分页
        pageSize:3,  //初始化页大小
        pageList:[3,5,8,10,20],  //页大小选项
        toolbar:'#ToolBar',
        columns:[[
            {checkbox: true,width:100,align:'right'},
            {field:'id',title:'编号',width:100,align:'right'},
            {field:'name',title:'用户名',width:100,align:'right'},
            {field:'telephone',title:'电话',width:100,align:'right'},
            {field:'isadmin',title:'是否管理员',width:100,align:'right'},
            {field:'age',title:'年龄',width:100,align:'right'},
            {field:'bh',title:'编辑|操作',width:100,align:'right',
                formatter:function (value, row, index) {
                    return "<a href='javascript:goEdit("+row.id+");'>修改</a> <a href='javascript:delet("+row.id+");'>删除</a>"
                }
            }
        ]]
    });
});
//实现绑定条件查询
function searchUser() {
    var inputname=$("#inname").val();
    var inputtel=$("#intel").val();
    $("#dg").datagrid(
        {
        queryParams:{//设置查询条件
            name:inputname,
            tel:inputtel
        }
    })
}