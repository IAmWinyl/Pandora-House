import React from "react";
import { useEffect, useState } from "react";

function Item({ item }) {
    let imgUrl = imgUrl = "/assets/" + item.imgUrl;
    return (
        <tr>
            <td><img src={imgUrl} /></td>
            <td>{item.id}</td>
            <td>{item.name}</td>
            <td>{item.type}</td>
            <td>{item.price}</td>
        </tr>   
    );
}

function ItemList({ itemList }) {
    const items = itemList.items.map(item =>
        <Item key={ item.id } item={ item } />
    );

    return (
        <table>
            <tbody>
                <tr>
                    <th> </th>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Type</th>
                    <th>Price</th>
                </tr>
                {items}
            </tbody>
        </table>
        
    );
}

function getItems() {
    return fetch('http://127.0.0.1:8081/api/items')
            .then((res) => res.json())
            .catch((err) => {
                console.log(err.message);
            });
}

export default function App() {
    const [items, setItems] = useState([{"id":"-1","name":"-1","type":0,"price":0.00}]);
    
    useEffect(() => {
        getItems()
            .then((items) => {
                setItems(items);
            });
        }, []);
    // if(fetchItemList(itemList) != 200) {
    //     let exampleItem1 = JSON.parse('{"id":"0","name":"Failed","type":0,"price":0.00}');
    //     let exampleItem2 = JSON.parse('{"id":"1","name":"Failed","type":0,"price":0.00}');
    //     let exampleList = [exampleItem1,exampleItem2]
    // }
    return (
        <>
        <ItemList itemList={{ items }} />
    </>
    );
  }
  