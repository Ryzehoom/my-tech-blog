import http, { USER_SERVICE_BASE } from './http';

export const login = (data: any) => {
    return http.post(`${USER_SERVICE_BASE}/login`, data);
};
