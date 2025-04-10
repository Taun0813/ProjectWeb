import { Navigate } from "react-router-dom";

const RequireAdmin = ({ children }) => {
  const user = JSON.parse(localStorage.getItem("user"));
  const isAdmin = user?.role === "true";

  if (!isAdmin) {
    return <Navigate to="/" replace />;
  }

  return children;
};

export default RequireAdmin;
