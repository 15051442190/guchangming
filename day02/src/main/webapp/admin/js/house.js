$(function(){
    //1.使用datagrid组件绑定数据
    $('#dg').datagrid({
        url:'getAllHouse',
        pagination:true,  //开启分页
        pageSize:3,  //初始化页大小
        pageList:[3,5,8,10,20],  //页大小选项
        toolbar:'#ToolBar',
        columns:[[
            {checkbox: true,width:100,align:'right'},
            {field:'id',title:'编号',width:100,align:'right'},
            {field:'title',title:'标题',width:100,align:'right'},
            {field:'dname',title:'区域',width:100,align:'right'},
            {field:'sname',title:'街道',width:100,align:'right'},
            {field:'tname',title:'类型',width:100,align:'right'},
            {field:'price',title:'价格',width:100,align:'right'},
            {field:'floorage',title:'面积',width:100,align:'right'},
            {field:'ispass',title:'状态',width:100,align:'right',
                formatter:function (value, row, index) {
                    return value==0?"未审核":"已审核";
                }
             },
            {field:'bh',title:'编辑|操作',width:100,align:'right',
                formatter:function (value, row, index) {
                    return "<a href='javascript:goPass("+row.id+");'>确认审核</a> <a href='javascript:delet("+row.id+");'>详情</a>"
                }
            }
        ]]
    });
});
//去通过审核出租房
function goPass(id) {
    /*发送异步请求*/
    $.post("updatePassState",{"id":id,"state":1},function (data) {
        if (data.result>0){
            $('#dg').datagrid("reload");
        }else {
            $.messager.alert('友情提示','审核失败，请联系管理员:13260601227!','info');
        }
    },"json")
}
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