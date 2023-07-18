const path = require("path")
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyPlugin = require("copy-webpack-plugin");

var BUILD_DIR = path.resolve(__dirname, 'src/main/frontend/public');
var APP_DIR = path.resolve(__dirname, 'src/main/frontend/src');

module.exports = {
    entry: APP_DIR + '/index.js',
    mode: 'development',
    output: {
        path: BUILD_DIR,
        filename: "js/bundle.js",
        publicPath: '/',
        clean: true,
    },
    plugins: [
      new HtmlWebpackPlugin({
        template: APP_DIR + '/index.html',
        inject: true,
      }),
      new CopyPlugin({
        patterns: [
            { from: APP_DIR + "/assets/*", to: BUILD_DIR + "/assets/[name][ext]" },
        ],
      }),
    ],
    devServer: {
      port: 9000,
      historyApiFallback: true
    },
    resolve: {
        modules: ['.', 'node_modules'],
        extensions: ['.js', '.jsx']
    },
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
              test: /\.(png|jpg|jpeg|webp|gif)$/,
              type: 'asset/resource',
              use: [
                {
                    loader: 'webp-loader'
                }
              ]
            },
            {
              test: /\.js$/,
              enforce: "pre",
              use: ["source-map-loader"],
            },
        ]
    },
}