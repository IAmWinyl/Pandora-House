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
            {
              test: /\.(sass|less|css)$/,
              use: ['style-loader', 'css-loader', 'less-loader']
            }, 
            {
              test: /\.(png|jpg|webp|gif|svg|mp4)$/,
              use: [{
                  loader: 'file-loader'
              },
              {
                  loader: 'webp-loader'
              }
    ]
            }

        ]
    },
    devServer: {
      port: 9000,
    },
    resolve: {
        modules: ['.', 'node_modules'],
        extensions: ['.js', '.jsx']
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