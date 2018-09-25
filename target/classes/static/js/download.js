function Trim(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

function file_download_btn() {

    if ($.trim($("#address").val()).length == 0) {
        layer.msg('请输入下载链接')
        return false;
    }
    layer.load();
    var options = {
        url: "search",
        type: 'post',
        dataType: 'json',
        data: {
            'url': Trim($("#address").val())
        },
        success: function(data) {
            layer.closeAll('loading');
            if (data.msg == '解析失败') {
                layer.msg('系统系统繁忙,请稍后再试', {
                    time: 100000,
                    offset: '200px'
                });
            }else if (data.msg = '解析成功') {
                window.location.href = context + data.data;
            }
        }
    };
    $.ajax(options);
    return false;
}