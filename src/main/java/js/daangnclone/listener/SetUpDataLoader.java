package js.daangnclone.listener;


import js.daangnclone.domain.area.Area;
import js.daangnclone.domain.area.AreaRepository;
import js.daangnclone.domain.category.Category;
import js.daangnclone.domain.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static js.daangnclone.cmn.CmnCons.*;

@Component
@RequiredArgsConstructor
public class SetUpDataLoader {

    private final AreaRepository areaRepository;
    private final CategoryRepository categoryRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initDateLoad() {
        Optional<Area> findArea = areaRepository.findById(KOREA_CODE);
        if (findArea.isEmpty()) {
            createAreaCode();
        }

        Optional<Category> findCategory = categoryRepository.findById(DIGITAL_DEVICE_CODE);
        if (findCategory.isEmpty()) {
            createCategoryCode();
        }
    }

    private void createAreaCode() {
        Area area1 = new Area(KOREA_CODE, KOREA_NAME, null);

        Area area2 = new Area(GYEONGGI_CODE, GYEONGGI_NAME, KOREA_CODE);

        Area area3 = new Area(GAPYEONG_CODE, GAPYEONG_NAME, GYEONGGI_CODE);
        Area area4 = new Area(GOYANG_DUKYANG_CODE, GOYANG_DUKYANG_NAME, GYEONGGI_CODE);
        Area area5 = new Area(GOYANG_ILSANDONG_CODE, GOYANG_ILSANDONG_NAME, GYEONGGI_CODE);
        Area area6 = new Area(GOYANG_ILSANSU_CODE, GOYANG_ILSANSU_NAME, GYEONGGI_CODE);
        Area area7 = new Area(GHACHUN_CODE, GHACHUN_NAME, GYEONGGI_CODE);
        Area area8 = new Area(GHYANGMYONG_CODE, GHYANGMYONG_NAME, GYEONGGI_CODE);
        Area area9 = new Area(GHYANGCHU_CODE, GHYANGCHU_NAME, GYEONGGI_CODE);
        Area area10 = new Area(GURI_CODE, GURI_NAME, GYEONGGI_CODE);
        Area area11 = new Area(GUNPO_CODE, GUNPO_NAME, GYEONGGI_CODE);
        Area area12 = new Area(GIMPO_CODE, GIMPO_NAME, GYEONGGI_CODE);
        Area area13 = new Area(NAMYANGCHU_CODE, NAMYANGCHU_NAME, GYEONGGI_CODE);
        Area area14 = new Area(DONGDUCHUN_CODE, DONGDUCHUN_NAME, GYEONGGI_CODE);
        Area area15 = new Area(BUCHUN_SOSA_CODE, BUCHUN_SOSA_NAME, GYEONGGI_CODE);
        Area area16 = new Area(BUCHUN_OJUNG_CODE, BUCHUN_OJUNG_NAME, GYEONGGI_CODE);
        Area area17 = new Area(BUCHUN_ONEMI_CODE, BUCHUN_ONEMI_NAME, GYEONGGI_CODE);
        Area area18 = new Area(SUNGNAM_BUNDANG_CODE, SUNGNAM_BUNDANG_NAME, GYEONGGI_CODE);
        Area area19 = new Area(SUNGNAM_SUJUNG_CODE, SUNGNAM_SUJUNG_NAME, GYEONGGI_CODE);
        Area area20 = new Area(SUNGNAME_CHUNGWON_CODE, SUNGNAME_CHUNGWON_NAME, GYEONGGI_CODE);
        Area area21 = new Area(SUWON_KOWNSUN_CODE, SUWON_KOWNSUN_NAME, GYEONGGI_CODE);
        Area area22 = new Area(SUNWON_YUNGTONG_CODE, SUNWON_YUNGTONG_NAME, GYEONGGI_CODE);
        Area area23 = new Area(SUWON_JANGAN_CODE, SUWON_JANGAN_NAME, GYEONGGI_CODE);
        Area area24 = new Area(SUWON_PALDAL_CODE, SUWON_PALDAL_NAME, GYEONGGI_CODE);
        Area area25 = new Area(SIHEUNG_CODE, SIHEUNG_NAME, GYEONGGI_CODE);
        Area area26 = new Area(ANSAN_DANWON_CODE, ANSAN_DANWON_NAME, GYEONGGI_CODE);
        Area area27 = new Area(ANSAN_SANGROK_CODE, ANSAN_SANGROK_NAME, GYEONGGI_CODE);
        Area area28 = new Area(ANSUNG_CODE, ANSUNG_NAME, GYEONGGI_CODE);
        Area area29 = new Area(ANYANG_DONGAN_CODE, ANYANG_DONGAN_NAME, GYEONGGI_CODE);
        Area area30 = new Area(ANYANG_MANAN_CODE, ANYANG_MANAN_NAME, GYEONGGI_CODE);
        Area area31 = new Area(YANGCHU_CODE, YANGCHU_NAME, GYEONGGI_CODE);
        Area area32 = new Area(YANGPYEONG_CODE, YANGPYEONG_NAME, GYEONGGI_CODE);
        Area area33 = new Area(YEOJU_CODE, YEOJU_NAME, GYEONGGI_CODE);
        Area area34 = new Area(YEONCHUN_CODE, YEONCHUN_NAME, GYEONGGI_CODE);
        Area area35 = new Area(OSAN_CODE, OSAN_NAME, GYEONGGI_CODE);
        Area area36 = new Area(YONGIN_KIHEUNG_CODE, YONGIN_KIHEUNG_NAME, GYEONGGI_CODE);
        Area area37 = new Area(YONGIN_SUJI_CODE, YONGIN_SUJI_NAME, GYEONGGI_CODE);
        Area area38 = new Area(YONGIN_CHUIN_CODE, YONGIN_CHUIN_NAME, GYEONGGI_CODE);
        Area area39 = new Area(UIWANG_CODE, UIWANG_NAME, GYEONGGI_CODE);
        Area area40 = new Area(UIJUNGBU_CODE, UIJUNGBU_NAME, GYEONGGI_CODE);
        Area area41 = new Area(ECHUN_CODE, ECHUN_NAME, GYEONGGI_CODE);
        Area area42 = new Area(PAJU_CODE, PAJU_NAME, GYEONGGI_CODE);
        Area area43 = new Area(PYENGTAEK_CODE, PYENGTAEK_NAME, GYEONGGI_CODE);
        Area area44 = new Area(POCHUN_CODE, POCHUN_NAME, GYEONGGI_CODE);
        Area area45 = new Area(HANAM_CODE, HANAM_NAME, GYEONGGI_CODE);
        Area area46 = new Area(HWASUNG_CODE, HWASUNG_NAME, GYEONGGI_CODE);

        Area area47 = new Area(SEOUL_CODE, SEOUL_NAME, KOREA_CODE);
        Area area48 = new Area(GANGNAM_CODE, GANGNAM_NAME, SEOUL_CODE);

        List<Area> areaList = Arrays.asList(area1, area2, area3, area4, area5, area6, area7, area8, area9, area10, area11, area12, area13, area14,
                area15, area16, area17, area18, area19, area20, area21, area22, area23, area24, area25, area26, area27,
                area28, area29, area30, area31, area32, area33, area34, area35, area36, area37, area38, area39, area40,
                area41, area42, area43, area44, area45, area46, area47, area48);

        areaRepository.saveAll(areaList);

    }

    private void createCategoryCode() {
        Category category1 = new Category(DIGITAL_DEVICE_CODE, DIGITAL_DEVICE_NAME);
        Category category2 = new Category(HOUSEHOLD_APPLIANCES_CODE, HOUSEHOLD_APPLIANCES_NAME);
        Category category3 = new Category(FURNITURE_CODE, FURNITURE_NAME);
        Category category4 = new Category(LIVING_KITCHEN_CODE, LIVING_KITCHEN_NAME);
        Category category5 = new Category(CHILD_ITEM_CODE, CHILD_ITEM_NAME);
        Category category6 = new Category(CHILD_BOOKS_CODE, CHILD_BOOKS_NAME);
        Category category7 = new Category(WOMEN_CLOTHING_CODE, WOMEN_CLOTHING_NAME);
        Category category8 = new Category(MEN_CLOTHING_CODE, MEN_CLOTHING_NAME);
        Category category9 = new Category(SPORT_LEISURE_CODE, SPORT_LEISURE_NAME);
        Category category10 = new Category(BOOKS_CODE, BOOKS_NAME);

        List<Category> categoryList = Arrays.asList(category1, category2, category3, category4, category5, category6, category7, category8, category9, category10);
        categoryRepository.saveAll(categoryList);

    }

}
