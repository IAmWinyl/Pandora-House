import React from "react";
import { Link } from 'react-router-dom';
import './navbar.css';


const Navbar = () => {
    return (
        <div className="navbar__container layout__padding">
            <div className="navbar__list">
                <nav>
                    <div><Link to='/'>New</Link></div>
                    <div><Link to='/shop'>Bestsellers</Link></div>
                    <div><Link to="/shop">Clothing</Link></div>
                    <div><Link to='/shop'>Shoes</Link></div>
                    <div><Link to='/shop'>Accessories</Link></div>
                    <div><Link to='/shop'>Beauty</Link></div>
                    <div><Link to='/shop'>Home</Link></div>
                    <div><Link to='/shop'>Sale</Link></div>
                </nav>
            </div>
        </div>
    )
}

export default Navbar