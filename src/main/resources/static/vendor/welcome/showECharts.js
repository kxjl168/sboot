//设备占出比
var option1 = {
    title: {
        text: '510',
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
    tooltip: {
        trigger: 'item',
        formatter: "{d}%"
    },
    legend: {
        orient: 'vertical',
        x: 'left',
        data: ['开关设备开启量', '开关设备关闭量', '调节设备开启量', '调节设备关闭量'],
        itemWidth: 20,
        itemHeight: 12,
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
            value: 125,
            name: '开关设备开启量',
            itemStyle: {
                color: '#fb6e56'
            }
        },
            {
                value: 105,
                name: '开关设备关闭量',
                itemStyle: {
                    color: '#52626b'
                }
            },
            {
                value: 280,
                name: '调节设备开启量',
                itemStyle: {
                    color: '#13d1be',
                }
            },
            {
                value: 280,
                name: '调节设备关闭量',
                itemStyle: {
                    color: '#d17ecc',
                }
            }
        ],
    }],

};
//初始化echarts实例
var myChart1 = echarts.init(document.getElementById('chartsb'));

//使用制定的配置项和数据显示图表
myChart1.setOption(option1);


//区域设备使用统计
var option2 = {

    tooltip: {
        trigger: 'axis',
        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    legend: {
        data: ['区域设备使用量'],
        itemWidth: 20,
        itemHeight: 12,
    },

    calculable: true,
    xAxis: [
        {
            type: 'category',
            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
        }
    ],
    yAxis: [
        {
            type: 'value',
            name: '使用(次)'
        }
    ],
    series: [
        {
            name: '区域设备使用量',
            type: 'bar',
            data: [],
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
};

//初始化echarts实例
var myChart2 = echarts.init(document.getElementById('chartqy'));

//使用制定的配置项和数据显示图表
myChart2.setOption(option2);
//场景设备使用统计
var option3 = {

    tooltip: {
        trigger: 'axis',
        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    legend: {
        data: ['场景设备使用量'],
        itemWidth: 20,
        itemHeight: 12,
    },

    calculable: true,
    xAxis: [
        {
            type: 'category',
            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
        }
    ],
    yAxis: [
        {
            type: 'value',
            name: '使用(次)'
        }
    ],
    series: [
        {
            name: '场景设备使用量',
            type: 'bar',
            data: [],
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
};

//初始化echarts实例
var myChart3 = echarts.init(document.getElementById('chartcj'));

//使用制定的配置项和数据显示图表
myChart3.setOption(option3);