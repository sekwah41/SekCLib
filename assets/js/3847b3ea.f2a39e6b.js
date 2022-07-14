"use strict";(self.webpackChunksekclib_docs=self.webpackChunksekclib_docs||[]).push([[581],{3905:function(e,t,r){r.d(t,{Zo:function(){return s},kt:function(){return m}});var n=r(7294);function o(e,t,r){return t in e?Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[t]=r,e}function a(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),r.push.apply(r,n)}return r}function i(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?a(Object(r),!0).forEach((function(t){o(e,t,r[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):a(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function u(e,t){if(null==e)return{};var r,n,o=function(e,t){if(null==e)return{};var r,n,o={},a=Object.keys(e);for(n=0;n<a.length;n++)r=a[n],t.indexOf(r)>=0||(o[r]=e[r]);return o}(e,t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(n=0;n<a.length;n++)r=a[n],t.indexOf(r)>=0||Object.prototype.propertyIsEnumerable.call(e,r)&&(o[r]=e[r])}return o}var l=n.createContext({}),c=function(e){var t=n.useContext(l),r=t;return e&&(r="function"==typeof e?e(t):i(i({},t),e)),r},s=function(e){var t=c(e.components);return n.createElement(l.Provider,{value:t},e.children)},d={inlineCode:"code",wrapper:function(e){var t=e.children;return n.createElement(n.Fragment,{},t)}},p=n.forwardRef((function(e,t){var r=e.components,o=e.mdxType,a=e.originalType,l=e.parentName,s=u(e,["components","mdxType","originalType","parentName"]),p=c(r),m=o,f=p["".concat(l,".").concat(m)]||p[m]||d[m]||a;return r?n.createElement(f,i(i({ref:t},s),{},{components:r})):n.createElement(f,i({ref:t},s))}));function m(e,t){var r=arguments,o=t&&t.mdxType;if("string"==typeof e||o){var a=r.length,i=new Array(a);i[0]=p;var u={};for(var l in t)hasOwnProperty.call(t,l)&&(u[l]=t[l]);u.originalType=e,u.mdxType="string"==typeof e?e:o,i[1]=u;for(var c=2;c<a;c++)i[c]=r[c];return n.createElement.apply(null,i)}return n.createElement.apply(null,r)}p.displayName="MDXCreateElement"},1959:function(e,t,r){r.r(t),r.d(t,{assets:function(){return l},contentTitle:function(){return i},default:function(){return d},frontMatter:function(){return a},metadata:function(){return u},toc:function(){return c}});var n=r(3117),o=(r(7294),r(3905));const a={sidebar_position:1,description:"Info on how to add the library to your mod"},i="Adding the library to your mod",u={unversionedId:"setup",id:"setup",title:"Adding the library to your mod",description:"Info on how to add the library to your mod",source:"@site/docs/setup.md",sourceDirName:".",slug:"/setup",permalink:"/docs/setup",draft:!1,editUrl:"https://github.com/sekwah41/SekCLib/edit/master/docs/docs/setup.md",tags:[],version:"current",sidebarPosition:1,frontMatter:{sidebar_position:1,description:"Info on how to add the library to your mod"},sidebar:"tutorialSidebar",next:{title:"Contributing",permalink:"/docs/contributing"}},l={},c=[{value:"build.gradle (Maven)",id:"buildgradle-maven",level:2}],s={toc:c};function d(e){let{components:t,...r}=e;return(0,o.kt)("wrapper",(0,n.Z)({},s,r,{components:t,mdxType:"MDXLayout"}),(0,o.kt)("h1",{id:"adding-the-library-to-your-mod"},"Adding the library to your mod"),(0,o.kt)("p",null,"At the moment we only have plans to support the latest versions of mc and forge."),(0,o.kt)("p",null,"If you want older versions of mc please check if the features you need are already supported in older versions."),(0,o.kt)("h2",{id:"buildgradle-maven"},"build.gradle (Maven)"),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre",className:"language-groovy"},'repositories {\n    maven { url = "https://maven.sekwah.com" }\n}\n')),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre",className:"language-groovy"},'dependencies {\n    implementation fg.deobf("com.sekwah:SekCLib:0.2.0") // Remember to check the latest version as this may not be up to date.\n}\n')),(0,o.kt)("p",null,"Don't forget to also add the entry to your mods.toml file too."),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre",className:"language-yaml"},'[[dependencies.yourmodhere]]\n    modId="sekclib"\n    mandatory=true\n    versionRange="[0.2.0,)"\n    ordering="NONE"\n    side="BOTH"\n')))}d.isMDXComponent=!0}}]);