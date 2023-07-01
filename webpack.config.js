const path = require("path")
const HtmlWebpackPlugin = require('html-webpack-plugin');

var BUILD_DIR = path.resolve(__dirname, 'src/main/frontend/public');
var APP_DIR = path.resolve(__dirname, 'src/main/frontend/src');
module.exports = {
    entry: APP_DIR + '/index.js',
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
    resolve: {
        extensions: ['.*', '.js']
    },
    plugins: [
      new HtmlWebpackPlugin({
        template: BUILD_DIR + '/index.html',
        inject: false,
      })
    ],
    output: {
        path: BUILD_DIR,
        filename: "js/bundle.js"
    }
}