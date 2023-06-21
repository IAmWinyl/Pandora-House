const path = require("path")

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
    resolve: {
        extensions: ['*', '.js']
    },
    output: {
        path: path.resolve(__dirname, "src/main"),
        filename: "bundle.js"
    }
}