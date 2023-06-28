const path = require("path")
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    entry: "./src/main/index.js",
    mode: 'development',
    module: {
        rules: [
            {
              test: /\.(js|jsx)$/,
              exclude: /(node_modules)/,
              loader: 'babel-loader',
              options: { presets: ['@babel/preset-env','@babel/preset-react'] },
            },
        ]
    },
    devServer: {
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, PATCH, OPTIONS",
        "Access-Control-Allow-Headers": "X-Requested-With, content-type, Authorization"
      },
      port: 9000,
    },
    resolve: {
        extensions: ['.*', '.js']
    },
    plugins: [
      new HtmlWebpackPlugin({
        template: '/src/main/resources/templates/index.html',
        inject: false,
      })
    ],
    output: {
        path: path.resolve(__dirname, "src/main/resources/templates"),
        filename: "app/bundle.js"
    }
}