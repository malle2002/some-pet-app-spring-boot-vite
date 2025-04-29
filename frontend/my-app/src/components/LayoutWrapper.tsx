import React from 'react';
import { useLocation } from 'react-router-dom';
import AdminLayout from '../layouts/AdminLayout';
import DefaultLayout from '../layouts/DefaultLayout';

const LayoutWrapper: React.FC<{ children: React.ReactNode }> = ({ children }) => {
    const location = useLocation();

    const isAdminRoute = location.pathname.startsWith('/admin');

    return isAdminRoute ? (
        <AdminLayout>{children}</AdminLayout>
    ) : (
        <DefaultLayout>{children}</DefaultLayout>
    );
};

export default LayoutWrapper;
