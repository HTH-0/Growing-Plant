    package com.example.hth.domain;

    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class UserPlant {
        private Long id;
        private String userId;
        private Long plantTypeId;
        private int commitCount;
        private String growthStage;

        // commitCount로 growthStage를 변경하는 메서드
        public void applyCommit(int commitCount) {
            this.commitCount = commitCount;
            this.growthStage = calculateStage(commitCount);
        }
        // growthStage 기준
        private String calculateStage(int point) {
            if (point < 20) return "새싹";
            if (point < 50) return "작은 나무";
            if (point < 80) return "나무";
            return "큰 나무";
        }
    }

