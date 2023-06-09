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


// function Item({ item }) {
//     let imgUrl = imgUrl = "/assets/" + item.imgUrl;
//     return (
//         <tr>
//             <td><img src={imgUrl} /></td>
//             <td>{item.id}</td>
//             <td>{item.name}</td>
//             <td>{item.type}</td>
//             <td>{item.price}</td>
//         </tr>   
//     );
// }

// function ItemList({ itemList }) {
//     const items = itemList.items.map(item =>
//         <Item key={ item.id } item={ item } />
//     );

//     return (
//         <table>
//             <tbody>
//                 <tr>
//                     <th> </th>
//                     <th>ID</th>
//                     <th>Name</th>
//                     <th>Type</th>
//                     <th>Price</th>
//                 </tr>
//                 {items}
//             </tbody>
//         </table>
        
//     );
// }

// function getItems() {
//     return fetch('http://127.0.0.1:8081/api/items')
//             .then((res) => res.json())
//             .catch((err) => {
//                 console.log(err.message);
//             });
// }

// export default function App() {
//     const [items, setItems] = useState([{"id":"-1","name":"-1","type":0,"price":0.00}]);
    
//     useEffect(() => {
//         getItems()
//             .then((items) => {
//                 setItems(items);
//             });
//         }, []);
        
//     return (
//         <>
//         <ItemList itemList={{ items }} />
//     </>
//     );
//   }
