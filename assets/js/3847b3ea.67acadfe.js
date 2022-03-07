"use strict";(self.webpackChunksekclib_docs=self.webpackChunksekclib_docs||[]).push([[581],{3905:function(e,t,r){r.d(t,{kt:function(){return p}});var n=r(7294);function o(e,t,r){return t in e?Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[t]=r,e}function a(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),r.push.apply(r,n)}return r}function i(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?a(Object(r),!0).forEach((function(t){o(e,t,r[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):a(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function l(e,t){if(null==e)return{};var r,n,o=function(e,t){if(null==e)return{};var r,n,o={},a=Object.keys(e);for(n=0;n<a.length;n++)r=a[n],t.indexOf(r)>=0||(o[r]=e[r]);return o}(e,t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(n=0;n<a.length;n++)r=a[n],t.indexOf(r)>=0||Object.prototype.propertyIsEnumerable.call(e,r)&&(o[r]=e[r])}return o}var u=n.createContext({}),c=function(e){var t=n.useContext(u),r=t;return e&&(r="function"==typeof e?e(t):i(i({},t),e)),r},s={inlineCode:"code",wrapper:function(e){var t=e.children;return n.createElement(n.Fragment,{},t)}},d=n.forwardRef((function(e,t){var r=e.components,o=e.mdxType,a=e.originalType,u=e.parentName,d=l(e,["components","mdxType","originalType","parentName"]),p=c(r),m=o,f=p["".concat(u,".").concat(m)]||p[m]||s[m]||a;return r?n.createElement(f,i(i({ref:t},d),{},{components:r})):n.createElement(f,i({ref:t},d))}));function p(e,t){var r=arguments,o=t&&t.mdxType;if("string"==typeof e||o){var a=r.length,i=new Array(a);i[0]=d;var l={};for(var u in t)hasOwnProperty.call(t,u)&&(l[u]=t[u]);l.originalType=e,l.mdxType="string"==typeof e?e:o,i[1]=l;for(var c=2;c<a;c++)i[c]=r[c];return n.createElement.apply(null,i)}return n.createElement.apply(null,r)}d.displayName="MDXCreateElement"},1959:function(e,t,r){r.r(t),r.d(t,{assets:function(){return s},contentTitle:function(){return u},default:function(){return m},frontMatter:function(){return l},metadata:function(){return c},toc:function(){return d}});var n=r(3117),o=r(102),a=(r(7294),r(3905)),i=["components"],l={sidebar_position:1,description:"Info on how to add the library to your mod"},u="Adding the library to your mod",c={unversionedId:"setup",id:"setup",title:"Adding the library to your mod",description:"Info on how to add the library to your mod",source:"@site/docs/setup.md",sourceDirName:".",slug:"/setup",permalink:"/docs/setup",editUrl:"https://github.com/sekwah41/SekCLib/edit/master/docs/docs/setup.md",tags:[],version:"current",sidebarPosition:1,frontMatter:{sidebar_position:1,description:"Info on how to add the library to your mod"},sidebar:"tutorialSidebar",next:{title:"Contributing",permalink:"/docs/contributing"}},s={},d=[{value:"build.gradle (Maven)",id:"buildgradle-maven",level:2}],p={toc:d};function m(e){var t=e.components,r=(0,o.Z)(e,i);return(0,a.kt)("wrapper",(0,n.Z)({},p,r,{components:t,mdxType:"MDXLayout"}),(0,a.kt)("h1",{id:"adding-the-library-to-your-mod"},"Adding the library to your mod"),(0,a.kt)("p",null,"At the moment we only have plans to support the latest versions of mc and forge."),(0,a.kt)("p",null,"If you want older versions of mc please check if the features you need are already supported in older versions."),(0,a.kt)("h2",{id:"buildgradle-maven"},"build.gradle (Maven)"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-groovy"},'repositories {\n    maven { url = "https://maven.sekwah.com" }\n}\n')),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-groovy"},'dependencies {\n    implementation fg.deobf("com.sekwah:SekCLib:0.2.0") // Remember to check the latest version as this may not be up to date.\n}\n')),(0,a.kt)("p",null,"Don't forget to also add the entry to your mods.toml file too."),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-yaml"},'[[dependencies.yourmodhere]]\n    modId="sekclib"\n    mandatory=true\n    versionRange="[0.2.0,)"\n    ordering="NONE"\n    side="BOTH"\n')))}m.isMDXComponent=!0}}]);