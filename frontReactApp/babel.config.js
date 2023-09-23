// babel.config.js

module.exports = function(api) {
    const isProd = process.env.NODE_ENV === 'production';
  
    // Cache configuration
    api.cache(true);
  
    // Preset and plugin configuration
    const presets = ['@babel/preset-env', '@babel/preset-react'];
    const plugins = ['@babel/plugin-proposal-class-properties'];
  
    return {
      presets,
      plugins
    };
  };
  