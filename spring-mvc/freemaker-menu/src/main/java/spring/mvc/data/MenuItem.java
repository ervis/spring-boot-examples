package spring.mvc.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {
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
    String url;

    @NonNull
    String icon;
}
