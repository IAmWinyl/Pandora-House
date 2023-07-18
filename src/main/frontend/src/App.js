import React from "react";
import { Routes, Route } from 'react-router-dom';

import { Home, Shop, NoMatch } from './pages';
import { Layout } from './components';
import './App.css';


export default function App() {
    return (
        <Routes>
            <Route path="/" element={<Layout />}>
                <Route path="shop" element={<Shop />} />
                <Route index element={<Home />} />
                <Route path="/shop" element={<Shop />} />
                <Route path="*" element={<NoMatch />} />
            </Route>
        </Routes>
    )
}