var webpack = require('webpack');

module.exports = {
  entry: [
    './src/index.jsx',
    'bootstrap-loader'
  ],
  module: {
    loaders: [
      {
        test: /\.jsx?$/,
        exclude: /node_modules/,
        loader: 'babel'
      },
      { 
         test: /notifyjs\.js$/,
         loader: 'imports?jQuery=jquery,$=jquery,this=>window'
      },
      { test: /\.css$/, loaders: [ 'style', 'css' ] },
      { test: /\.scss$/, loaders: [ 'style', 'css', 'sass' ] },
      { test: /\.(woff2?|svg)$/, loader: 'url?limit=10000' },
      { test: /\.(ttf|eot)$/, loader: 'file' },
      { test: /bootstrap-sass\/assets\/javascripts\//, loader: 'imports?jQuery=jquery' }
    ]
  },
  resolve: {
    extensions: ['', '.js', '.jsx']
  },
  output: {
    path: __dirname + '/dist',
    publicPath: '/',
    filename: 'bundle.js'
  },
  plugins: [
  ]
};
