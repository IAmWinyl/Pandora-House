import React, { Component } from "react";
import './shop.css';

const imagesList = [
    {
      id: 1,
      src: "assets/runway1.webp",
      alt: "runway1",
    },
    {
      id: 2,
      src: "assets/runway2.webp",
      alt: "runway2",
    },
    {
      id: 3,
      src: "assets/runway3.webp",
      alt: "runway3",
    },
  ];

export default class Shop extends Component {
    state = { imagesList }

    render () {
        return (
            <div className="shop">
                {imagesList.map((image) => (
                    <img key={image.id} src={image.src} alt={image.alt} />
                ))}
            </div>
        )
    }
}