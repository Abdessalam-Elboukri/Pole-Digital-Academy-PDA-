/** @type {import('tailwindcss').Config} */
const defaultTheme = require('tailwindcss/defaultTheme')

module.exports = {
  content: ["./src/main/webapp/**/*.jsp",'./src/main/webapp/WEB-INF/**/*.jsp','./node_modules/tw-elements/dist/js/**/*.js'],
  theme: {
    extend: {
        fontFamily: {
          sans: ['Inter', 'sans-serif',"Inter var", ...defaultTheme.fontFamily.sans],
        },
      }

  },
  plugins: [
    require('tw-elements/dist/plugin')
  ]
}
