import { Route, Routes } from "react-router-dom";
import links from "./utils/links";
import Layout from "./components/layout/Layout";
import Home from "./components/pages/Home";
import NotFound from "./components/pages/NotFound";
import UnderConstruction from "./components/pages/UnderConstruction";
import About from "./components/pages/About";

const App = () => {
  return (
    <>
      <Layout>
        <Routes>
          <Route path={links.get("home").path} element={<UnderConstruction />} />
          <Route
            path={links.get("about").path}
            element={<About />}
          />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </Layout>
    </>
  );
};

export default App;
