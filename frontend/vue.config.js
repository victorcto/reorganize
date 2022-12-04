const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true, 
  // configureWebpack: {
  //   watch: true
  // },
  devServer: {
    // host: 'localhost',
    port: 8080,
    proxy: 'http://host.docker.internal:8082'
  }
})
