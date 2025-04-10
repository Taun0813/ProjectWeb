import { useEffect, useState } from "react";
import ReactPaginate from "react-paginate";
import Product from "./Product/Product";
import "./Products.css";
import { useGlobalContext } from "@/components/GlobalContext/GlobalContext";
import Skeleton from "react-loading-skeleton";

const Products = () => {
  const { store } = useGlobalContext();
  const [currentPage, setCurrentPage] = useState(0); // 0-based index
  const [totalPages, setTotalPages] = useState(1);
  const productsPerPage = 9;

  useEffect(() => {
    store.getProductsByPage(currentPage + 1, productsPerPage).then((res) => {
      if (res?.totalPages) {
        setTotalPages(res.totalPages);
      }
    });
  }, [currentPage]);
  
  
  

  const handlePageClick = (event) => {
    setCurrentPage(event.selected);
  };

  return (
    <div className="sub-container" id="products">
      <h2>Headphones For You</h2>
      {store.state.products.length > 0 ? (
        <>
          <div className="contains-product">
            {store.state.products.map((product) => (
              <Product key={product.id} product={product} />
            ))}
          </div>
          <div className="mt-4 flex justify-center">
            <ReactPaginate
              previousLabel={"←"}
              nextLabel={"→"}
              breakLabel={"..."}
              pageCount={totalPages}
              marginPagesDisplayed={1}
              pageRangeDisplayed={3}
              onPageChange={handlePageClick}
              containerClassName={"pagination"}
              activeClassName={"active"}
              pageClassName={"page-item"}
              previousClassName={"page-prev"}
              nextClassName={"page-next"}
            />
          </div>
        </>
      ) : (
        <div className="skeleton">
          <Skeleton height={250} />
        </div>
      )}
    </div>
  );
};

export default Products;
