import React, { Component } from 'react'

const Header = (props) => (
    <h1>{props.title}</h1>
);

class ListBooks extends Component {



    render() {

        const {books} = this.props

        return (
            <React.Fragment>
                <Header title={'Books in Library'}/>
                <table className="books">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>ISBN</th>
                        <th>Author</th>
                        <th>Genre</th>
                        <th>Actions...</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        books.map((book, i) => (
                            <tr key={i}>
                                <td>{book.id}</td>
                                <td>{book.title}</td>
                                <td>{book.isbn}</td>
                                <td>{book.author.name}</td>
                                <td>{book.genre.name}</td>
                            </tr>
                        ))
                    }
                    </tbody>
                </table>
            </React.Fragment>
        )
    }
}

export default  ListBooks