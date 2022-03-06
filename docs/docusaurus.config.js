// @ts-check
// Note: type annotations allow type checking and IDEs autocompletion

const lightCodeTheme = require('prism-react-renderer/themes/github');
const darkCodeTheme = require('prism-react-renderer/themes/dracula');

/** @type {import('@docusaurus/types').Config} */
const config = {
  title: 'Sekwah41\'s Core Library',
  tagline: 'A library for helping with a bunch of things',
  url: 'https://sekclib.sekwah.com/',
  baseUrl: '/',
  onBrokenLinks: 'throw',
  onBrokenMarkdownLinks: 'warn',
  favicon: 'img/favicon.ico',
  organizationName: 'sekwah41', // Usually your GitHub org/user name.
  projectName: 'SekCLib', // Usually your repo name.

  presets: [
    [
      'classic',
      /** @type {import('@docusaurus/preset-classic').Options} */
      ({
        docs: {
          sidebarPath: require.resolve('./sidebars.js'),
          // Please change this to your repo.
          editUrl: 'https://github.com/sekwah41/SekCLib/edit/master/docs/',
        },
        blog: false,
        theme: {
          customCss: require.resolve('./src/css/custom.css'),
        },
      }),
    ],
  ],

  themeConfig:
    /** @type {import('@docusaurus/preset-classic').ThemeConfig} */
    ({
      navbar: {
        title: 'SekC Lib',
        logo: {
          alt: 'SekC Lib Logo',
          src: 'img/sekclib.png',
        },
        items: [
          // {
          //   to: '/docs/recode',
          //   position: 'left',
          //   label: 'Recode Info',
          // },
          // {
          //   to: '/docs/getting_started',
          //   position: 'left',
          //   label: 'Getting Started',
          // },
          //{to: '/blog', label: 'Blog', position: 'left'},
          //{to: '/docs/commands', label: 'Commands', position: 'left'},
          //{to: '/docs/portal-tags', label: 'Portal Tags', position: 'left'},
          {
            href: 'https://github.com/sekwah41/SekCLib',
            label: 'GitHub',
            position: 'right',
          },
        ],
      },
      footer: {
        style: 'dark',
        links: [
          {
            title: 'Downloads',
            items: [
              {
                label: 'CurseForge',
                href: 'https://www.curseforge.com/minecraft/mc-mods/sekclib',
              },
              {
                label: 'GitHub',
                href: 'https://github.com/sekwah41/SekCLib/releases',
              },
            ],
          },
          {
            title: 'Community',
            items: [
              {
                label: 'Discord',
                href: 'https://discord.sekwah.com/',
              },
              {
                label: 'Twitter',
                href: 'https://twitter.com/sekwah',
              },
            ],
          },
          {
            title: 'More',
            items: [
              {
                label: 'Changelog',
                href: 'https://github.com/sekwah41/SekCLib/blob/master/CHANGELOG.md',
              },
              {
                label: 'Source Code',
                href: 'https://github.com/sekwah41/SekCLib',
              },
            ],
          },
        ],
        copyright: `Copyright © ${new Date().getFullYear()} Sekwah.`,
      },
      prism: {
        theme: lightCodeTheme,
        darkTheme: darkCodeTheme,
      }
    }),
};

module.exports = config;
