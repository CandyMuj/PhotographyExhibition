module.exports = {
    baseUrl: './',
    assetsDir: 'static',
    productionSourceMap: false,
    devServer: {
        port: 9002,
        open: false
        //     proxy: {
        //         '/api':{
        //             target:'http://jsonplaceholder.typicode.com',
        //             changeOrigin:true,
        //             pathRewrite:{
        //                 '/api':''
        //             }
        //         }
        //     }
    }
};
