import React from 'react';
import clsx from 'clsx';
import styles from './HomepageFeatures.module.css';

const FeatureList = [
  {
    title: 'Automatic Data Syncing',
    Svg: require('../../static/img/image1.svg').default,
    description: (
      <>
          Spend more time making mods rather than worrying about properly syncing data.
      </>
    ),
  },
  {
    title: 'Ability System (Planned)',
    Svg: require('../../static/img/image2.svg').default,
    description: (
      <>
          Currently available and usable as part of the <a href='https://www.curseforge.com/minecraft/mc-mods/sekwah41s-naruto-mod'>Naruto Mod</a>.
          We have plans to separate this out into the library as it has been requested for use in other mods.
      </>
    ),
  },
  {
    title: 'Custom Animations (Planned)',
    Svg: require('../../static/img/image3.svg').default,
    description: (
      <>
          We are currently in the process of remaking the animation system. For previews of what it used to look like check out the old 1.7.10 copies.
      </>
    ),
  },
];

function Feature({Svg, title, description}) {
  return (
    <div className={clsx('col col--4')}>
      <div className="text--center">
        <Svg className={styles.featureSvg} alt={title} />
      </div>
      <div className="text--center padding-horiz--md">
        <h3>{title}</h3>
        <p>{description}</p>
      </div>
    </div>
  );
}

export default function HomepageFeatures() {
  return (
    <section className={styles.features}>
      <div className="container">
        <div className="row">
          {FeatureList.map((props, idx) => (
            <Feature key={idx} {...props} />
          ))}
        </div>
      </div>
    </section>
  );
}
