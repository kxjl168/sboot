/**
 * 获得区域统计数据
 * @param year
 */
var regionLogCountList = getRegionLogCountList();

var sceneLogCountList = getSceneLogCountList();

var deviceStatusCount = getDeviceStatusCountList ();

function getRegionLogCountList(year) {
    console.log("getRegionLogCountList:" + year)
    $.post('/manager/user/getRegionLogCountList', {"year": year}).done(
        function (data) {
            var echartsData = [];
            for (var i = 0; i < data.length; i++) {
                echartsData[i] = data[i].count;
            }
            // 填入数据
            myChart2.setOption({
                series: [
                    {
                        name: '区域设备使用量',
                        type: 'bar',
                        data: echartsData,
                        markPoint: {
                            data: [
                                {type: 'max', name: '最大值'},
                                {type: 'min', name: '最小值'}
                            ]
                        },
                        itemStyle: {
                            color: '#1cb09a'
                        }
                    }
                ]
            });
        });
}

function getSceneLogCountList(year) {
    console.log("getSceneLogCountList:" + year)
    $.post('/manager/user/getSceneLogCountList', {"year": year}).done(
        function (data) {
            var echartsData = [];
            for (var i = 0; i < data.length; i++) {
                echartsData[i] = data[i].count;
            }
            // 填入数据
            myChart3.setOption({
                series: [
                    {
                        name: '场景设备使用量',
                        type: 'bar',
                        data: echartsData,
                        markPoint: {
                            data: [
                                {type: 'max', name: '最大值'},
                                {type: 'min', name: '最小值'}
                            ]
                        },
                        itemStyle: {
                            color: '#1cb09a'
                        }
                    }
                ]
            });
        });
}

function getDeviceStatusCountList() {
    $.post('/manager/user/getDeviceStatusCountList').done(
        function (data) {
            console.log(data.switchgearOpenCount);

            // 填入数据

            myChart1.setOption({

                title: {
                    text: data.switchgearOpenCount + data.switchgearCloseCount + data.adjustCloseCount + data.adjustOpenCount,
                    subtext: '操作总数',
                    x: 'center',
                    y: '110',
                    itemGap: 10,
                    textStyle: {
                        color: '#444444',
                        fontFamily: '微软雅黑',
                        fontSize: 35,
                        fontWeight: 'bolder'
                    },
                    subtextStyle: {
                        color: '#888888' // 副标题文字颜色
                    }
                },

                series: [{
                    name: '',
                    type: 'pie',
                    radius: ['60%', '75%'],
                    label: {
                        normal: {
                            formatter: '{b|{b}：}{c}',
                            rich: {
                                b: {
                                    fontSize: 14,
                                    lineHeight: 3
                                },

                            }
                        }
                    },
                    data: [{
                        value: data.switchgearOpenCount,
                        name: '开关设备开启量',
                        itemStyle: {
                            color: '#fb6e56'
                        }
                    },
                        {
                            value: data.switchgearCloseCount,
                            name: '开关设备关闭量',
                            itemStyle: {
                                color: '#52626b'
                            }
                        },
                        {
                            value: data.adjustOpenCount,
                            name: '调节设备开启量',
                            itemStyle: {
                                color: '#13d1be',
                            }
                        },
                        {
                            value: data.adjustCloseCount,
                            name: '调节设备关闭量',
                            itemStyle: {
                                color: '#d17ecc',
                            }
                        }
                    ],
                }],
            });
        });
}