import axios from 'axios';
import { useAuthStore } from '../stores/auth';

// Create axios instance
const http = axios.create({
    // We don't set a strict baseURL here because we need to hit different ports
    // But the user asked to set it to http://115.159.59.30, so we can do that if we were using a gateway.
    // Given the instruction "暂时可以直接写全路径", we will allow full URLs to override.
    // We use relative path to trigger the proxy
    baseURL: '',
    timeout: 10000,
});

// Request interceptor
http.interceptors.request.use(
    (config) => {
        const authStore = useAuthStore();
        if (authStore.token) {
            config.headers.Authorization = `Bearer ${authStore.token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

// Response interceptor
http.interceptors.response.use(
    (response) => {
        return response;
    },
    (error) => {
        if (error.response && error.response.status === 401) {
            const authStore = useAuthStore();
            authStore.logout();
        }
        return Promise.reject(error);
    }
);

export default http;

export const USER_SERVICE_BASE = '/user-api';
export const ARTICLE_SERVICE_BASE = '/article-api';
