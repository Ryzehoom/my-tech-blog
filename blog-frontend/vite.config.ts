import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    proxy: {
      '/user-api': {
        target: 'http://115.159.59.30:8081/api/user',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/user-api/, '')
      },
      '/article-api': {
        target: 'http://115.159.59.30:8082/api/article',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/article-api/, '')
      }
    }
  }
})
