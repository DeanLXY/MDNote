##MD-笔记(自用)

##FAB 切换效果
1. 引入库

    `compile 'konifar:fab-transformation:1.0.0'`

2. 打开& 关闭效果

       if (v == overlay) {
                FabTransformation.with(fab).setOverlay(overlay).transformFrom(rv_ruuning_category);
        } else if (v == fab) {

            FabTransformation.with(fab).setOverlay(overlay).transformTo(rv_ruuning_category);
        }