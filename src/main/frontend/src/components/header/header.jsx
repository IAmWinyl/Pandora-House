import React from "react";
import './header.css';
import { Link } from 'react-router-dom';


const Header = () => {
    return (
        <div className="header">
            <div className="header__elem"></div>
            <div className="header__logo">
                <p><Link to='/'>PANDORA HOUSE</Link></p>
            </div>
            <div className="header__interactions">
                <div className="header__search__bg">
                    <div className="header__searchicon">
                        <svg viewBox="0 0 20 20" className="header__searchicon__svg">
                            <g stroke="none" strokeWidth="1" fillRule="evenodd">
                            <path d="M8.5,2 
                                C 12.0898509,2 15,4.91014913 15,8.5 
                                C 15,10.1148687 14.4111063,11.592194 13.4363556,12.7289391 
                                L 18.0134785,17.3063718 L17.3063718,18.0134785 L12.7289391,13.4363556 
                                C 11.592194,14.4111063 10.1148687,15 8.5,15 
                                C 4.91014913,15 2,12.0898509 2,8.5 
                                C 2,4.91014913 4.91014913,2 8.5,2 Z 
                                M 8.5,3 
                                C 5.46243388,3 3,5.46243388 3,8.5 
                                C 3,11.5375661 5.46243388,14 8.5,14 
                                C 11.5375661,14 14,11.5375661 14,8.5 
                                C 14,5.46243388 11.5375661,3 8.5,3 Z" fillRule="nonzero">
                            </path>
                            </g>
                        </svg>
                    </div>
                    <div className="header__search__searchbox">
                        <form>
                            <input type="search" className="header__search__searchbox" placeholder="Search"></input>
                        </form>
                        
                    </div>
                </div>
                <div className="header__usericons__cart">
                    <a aria-label="Shopping Bag" href="/">
                        <div className="header__usericons__cart__container">
                            <svg className="header__cart__svg" viewBox="0 0 24 24">
                                <path d="M8 9V6a4 4 0 118 0v3h6v13H2V9h6zm1 0h6V6a3 3 0 00-6 0v3zm-1 1H3v11h5V10zm1 0v11h6V10H9zm7 0v11h5V10h-5z"></path>
                            </svg>
                        </div>
                    </a>
                </div>
                <div className="header__usericons__avatar">
                    <svg xmlns="http://www.w3.org/2000/svg" className="icon icon-tabler icon-tabler-user-circle" width="24" height="24" viewBox="0 0 24 24" strokeWidth="1" stroke="currentColor" fill="none" strokeLinecap="round" strokeLinejoin="round">
                        <path stroke="none" d="M0 0h24v24H0z" fill="none"></path>
                        <path d="M12 12m-9 0a9 9 0 1 0 18 0a9 9 0 1 0 -18 0"></path>
                        <path d="M12 10m-3 0a3 3 0 1 0 6 0a3 3 0 1 0 -6 0"></path>
                        <path d="M6.168 18.849a4 4 0 0 1 3.832 -2.849h4a4 4 0 0 1 3.834 2.855"></path>
                    </svg>
                </div>
            </div>
        </div>
    )
}

export default Header