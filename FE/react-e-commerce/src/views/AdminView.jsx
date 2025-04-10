import { Link, useLocation } from "react-router-dom";

const AdminView = () => {
  

    const removeExpandedClass = () => {
      let mobileExpandedMenu = document.querySelector(".mobile-expanded-menu");
      if (mobileExpandedMenu) {
        mobileExpandedMenu.classList.remove("mobile-expanded");
      }
    };
  return (
    <div className="p-4 bg-white text-black">
      <h1 className="text-2xl font-bold mb-4">Admin Dashboard</h1>
      <Link to="/dashboard/product" onClick={removeExpandedClass} className="text-black">
        Products
      </Link>
    </div>

  );
};

export default AdminView;
