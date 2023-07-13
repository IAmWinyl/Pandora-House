import React from 'react';
import { Outlet } from 'react-router-dom';
import { Header, Navbar, Footer} from '../';

const Layout = () => {
    return (
        <div>
            <Header />
            <Navbar />
            <Outlet />
            <Footer />
        </div>
    )
}

export default Layout