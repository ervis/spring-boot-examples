package spring.mvc.data;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopLevelMenuItem {
    @NonNull
    String name;

    // i18nName
    @NonNull
    String title;

    @NonNull
    String permission;

    @NonNull
    Integer order;

    @NonNull
    String icon;

    Map<String, MenuItem> items;
}
