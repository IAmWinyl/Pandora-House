import React, { Component } from "react";
import { useState, useEffect } from "react";
import './shop.css'; 
import { Shop_Item as Item } from "../../components";

export default function Shop() {
  const [items, setItems] = useState([]);
  const [query, setQuery] = useState('');

  useEffect(() => {
    fetch('http://127.0.0.1:8081/api/items/list')
      .then((res) => res.json())
      .then((data) => {
        const item_list = data.map(item => item);
        setItems(item_list);
      })
      .catch((err) => {
          console.log(err.message);
      });
  }, []);

  console.log(items);
  return (
    <div className="shop section__padding">
        <div className="shop__sidebar">
            <div className="list">
                <h2>Sort</h2>
                <h4>Category</h4>
                <ul> 
                    <li><a href="/">T-shirts</a></li>
                    <li><a href="/">Pants</a></li>
                    <li><a href="/">Shorts</a></li>
                    <li><a href="/">Skirts</a></li>
                    <li><a href="/">Dresses</a></li>
                    <li><a href="/">Accessories</a></li>
                </ul>
            </div>
        </div>
        <div className="shop__content">
          {items.map(item => <Item key={ item.id } item={ item } />)}
        </div>
    </div>
  );
}