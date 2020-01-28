const webpack = require('webpack');
const path = require('path');

module.exports = {
    entry: {
        display: path.resolve(__dirname, 'js/display.js')
    },
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: '[name]Dist.js'
    },
    devtool: 'source-map',
    module: {
        rules: [
            {
                test: /\.css$/,
                use: [
                    { loader: 'style-loader' },
                    {
                        loader: 'css-loader',
                        options: {
                            modules: true
                        }
                    }
                ]
            }
        ]
    },
    plugins: [
        new webpack.ProvidePlugin({
            $: 'jquery',
            jQuery: 'jquery',
            jquery: 'jquery',
            'windows.jQuery': 'jquery'
        })
    ],
    mode: 'production'
};