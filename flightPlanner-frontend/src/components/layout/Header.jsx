import { useState } from 'react';
import { useLocation, Link } from 'react-router-dom';
import { faBars, faXmark } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import imageLogo from './assets/logo-nav.png';
import './css/Header.css';
import links from '../../utils/links';

const navLinks = Array.from(links.values()).filter(link => link.anchorable);

const Header = () => {
  const { pathName } = useLocation();
  const [navExpanded, setNavExpanded] = useState(false);

  return (
    <header className='site-header'>
      <nav className="container grid nav-bar">
        <Link className="nav-bar-logo" to={links.get('home').path}>
          <img src={imageLogo} alt="Flight Planner logo" />
        </Link>
        <button 
          className="nav-bar-hamburger" 
          type="button" 
          onClick={() => setNavExpanded(!navExpanded)}
        >
          {navExpanded ?
            <FontAwesomeIcon icon={faXmark} size="2x" /> : 
            <FontAwesomeIcon icon={faBars} size="2x" />}
        </button>
        <ul 
          className={navExpanded ? 'nav-bar-links expanded' : 'nav-bar-links'} 
          onClick={() => setNavExpanded(!navExpanded)}
        >
          {navLinks.map((navLink, index) => 
            <li key={index}>
              <Link 
                className={pathName === navLink.path ? 'current-location' : ''} 
                to={navLink.path}
              >
                {navLink.name}
              </Link>
            </li>
          )}
        </ul>
      </nav>
    </header>
  );
};

export default Header;